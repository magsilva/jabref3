/*
 All programs in this directory and subdirectories are published under the 
 GNU General Public License as described below.

 This program is free software; you can redistribute it and/or modify it 
 under the terms of the GNU General Public License as published by the Free 
 Software Foundation; either version 2 of the License, or (at your option) 
 any later version.

 This program is distributed in the hope that it will be useful, but WITHOUT 
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for 
 more details.

 You should have received a copy of the GNU General Public License along 
 with this program; if not, write to the Free Software Foundation, Inc., 59 
 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Further information about the GNU GPL is available at:
 http://www.gnu.org/copyleft/gpl.ja.html
 */

package net.sf.jabref.groups;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import javax.swing.undo.AbstractUndoableEdit;

import net.sf.jabref.*;
import net.sf.jabref.BibtexEntry;

public class GroupsTree extends JTree implements DragSourceListener,
        DropTargetListener, DragGestureListener, TreeExpansionListener {
    /** distance from component borders from which on autoscrolling starts. */
    private static final int dragScrollActivationMargin = 10;
    /** number of pixels to scroll each time handler is called. */
    private static final int dragScrollDistance = 5;
    /** time of last autoscroll event (for limiting speed). */
    private static long lastDragAutoscroll = 0L;
    /** minimum interval between two autoscroll events (for limiting speed). */
    private static final long minAutoscrollInterval = 50L;
    /**
     * the point on which the cursor is currently idling during a drag
     * operation.
     */
    private Point idlePoint;
    /** time since which cursor is idling. */
    private long idleStartTime = 0L;
    /** max. distance cursor may move in x or y direction while idling. */
    private static final int idleMargin = 1;
    /** idle time after which the node below is expanded. */
    private static final long idleTimeToExpandNode = 1000L;

    private GroupSelector groupSelector;
    private GroupTreeNode dragNode = null;

    private final GroupTreeCellRenderer cellRenderer = new GroupTreeCellRenderer();

    public GroupsTree(GroupSelector groupSelector) {
        this.groupSelector = groupSelector;
        DragGestureRecognizer dgr = DragSource.getDefaultDragSource()
                .createDefaultDragGestureRecognizer(this,
                        DnDConstants.ACTION_MOVE, this);
        // Eliminates right mouse clicks as valid actions
        dgr.setSourceActions(dgr.getSourceActions() & ~InputEvent.BUTTON3_MASK);
        DropTarget dropTarget = new DropTarget(this, this);
        setCellRenderer(cellRenderer);
        setFocusable(false);
        setToggleClickCount(0);
        ToolTipManager.sharedInstance().registerComponent(this);
        setShowsRootHandles(false);
        setVisibleRowCount(Globals.prefs.getInt("groupsVisibleRows"));
        getSelectionModel().setSelectionMode(
                TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        addTreeExpansionListener(this);
    }
    
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);
        // set expansion state
        Object root = newModel.getRoot();
        if (root == null)
            return;
        GroupTreeNode cursor;
        // might be a dummy (instanceof DefaultMutableTreeNode)
        if (!(root instanceof GroupTreeNode))
            return; 
        for (Enumeration e = ((GroupTreeNode)root).preorderEnumeration(); 
                e.hasMoreElements(); ) {
            cursor = (GroupTreeNode)e.nextElement();
            if (cursor.isExpanded)
                expandPath(new TreePath(cursor.getPath()));
        }
    }

    public void dragEnter(DragSourceDragEvent dsde) {
        // ignore
    }

    /** This is for moving of nodes within myself */
    public void dragOver(DragSourceDragEvent dsde) {
        final Point p = dsde.getLocation(); // screen coordinates!
        SwingUtilities.convertPointFromScreen(p, this);
        final TreePath path = getPathForLocation(p.x, p.y);
        if (path == null) {
            dsde.getDragSourceContext().setCursor(DragSource.DefaultMoveNoDrop);
            return;
        }
        final GroupTreeNode target = (GroupTreeNode) path
                .getLastPathComponent();
        if (target == null || dragNode.isNodeDescendant(target)
                || dragNode == target) {
            dsde.getDragSourceContext().setCursor(DragSource.DefaultMoveNoDrop);
            return;
        }
        dsde.getDragSourceContext().setCursor(DragSource.DefaultMoveDrop);
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
        // ignore
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
        dragNode = null;
    }

    public void dragExit(DragSourceEvent dse) {
        // ignore
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        // ignore
    }

    /** This handles dragging of nodes (from myself) or entries (from the table) */
    public void dragOver(DropTargetDragEvent dtde) {
        final Point cursor = dtde.getLocation();
        final long currentTime = System.currentTimeMillis();
        if (idlePoint == null)
            idlePoint = cursor;

        // determine node over which the user is dragging
        final TreePath path = getPathForLocation(cursor.x, cursor.y);
        final GroupTreeNode target = path == null ? null : (GroupTreeNode) path
                .getLastPathComponent();
        setHighlight1Cell(target);

        // accept or reject
        if (dtde.isDataFlavorSupported(GroupTreeNode.flavor)) {
            // accept: move nodes within tree
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else if (dtde
                .isDataFlavorSupported(TransferableEntrySelection.flavorInternal)) {
            // check if node accepts explicit assignment
            if (path == null) {
                dtde.rejectDrag();
            } else {
                // this would be the place to check if the dragging entries
                // maybe are in this group already, but I think that's not
                // worth the bother (DropTargetDragEvent does not provide
                // access to the drag object)...
                // it might even be irritating to the user.
                if (target.getGroup().supportsAdd()) {
                    // accept: assignment from EntryTable
                    dtde.acceptDrag(DnDConstants.ACTION_LINK);
                } else {
                    dtde.rejectDrag();
                }
            }
        } else {
            dtde.rejectDrag();
        }

        // auto open
        if (Math.abs(cursor.x - idlePoint.x) < idleMargin
                && Math.abs(cursor.y - idlePoint.y) < idleMargin) {
            if (currentTime - idleStartTime >= idleTimeToExpandNode) {
                if (path != null) {
                    expandPath(path);
                }
            }
        } else {
            idlePoint = cursor;
            idleStartTime = currentTime;
        }

        // autoscrolling
        if (currentTime - lastDragAutoscroll < minAutoscrollInterval)
            return;
        final Rectangle r = getVisibleRect();
        final boolean scrollUp = cursor.y - r.y < dragScrollActivationMargin;
        final boolean scrollDown = r.y + r.height - cursor.y < dragScrollActivationMargin;
        final boolean scrollLeft = cursor.x - r.x < dragScrollActivationMargin;
        final boolean scrollRight = r.x + r.width - cursor.x < dragScrollActivationMargin;
        if (scrollUp)
            r.translate(0, -dragScrollDistance);
        else if (scrollDown)
            r.translate(0, +dragScrollDistance);
        if (scrollLeft)
            r.translate(-dragScrollDistance, 0);
        else if (scrollRight)
            r.translate(+dragScrollDistance, 0);
        scrollRectToVisible(r);
        lastDragAutoscroll = currentTime;
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        // ignore
    }

    public void drop(DropTargetDropEvent dtde) {
        setHighlight1Cell(null);
        try {
            // initializations common to all flavors
            final Transferable transferable = dtde.getTransferable();
            final Point p = dtde.getLocation();
            final TreePath path = getPathForLocation(p.x, p.y);
            if (path == null) {
                dtde.rejectDrop();
                return;
            }
            final GroupTreeNode target = (GroupTreeNode) path
                    .getLastPathComponent();
            // check supported flavors
            if (transferable.isDataFlavorSupported(GroupTreeNode.flavor)) {
                GroupTreeNode source = (GroupTreeNode) transferable
                        .getTransferData(GroupTreeNode.flavor);
                if (source == target) {
                    dtde.rejectDrop(); // ignore this
                    return;
                }
                if (source.isNodeDescendant(target)) {
                    dtde.rejectDrop();
                    return;
                }
                Enumeration expandedPaths = groupSelector.getExpandedPaths();
                UndoableMoveGroup undo = new UndoableMoveGroup(groupSelector,
                        groupSelector.getGroupTreeRoot(), source, target,
                        target.getChildCount());
                target.add(source);
                dtde.getDropTargetContext().dropComplete(true);
                // update selection/expansion state
                groupSelector.revalidateGroups(new TreePath[] { new TreePath(
                        source.getPath()) }, refreshPaths(expandedPaths));
                groupSelector.concludeMoveGroup(undo, source);
            } else if (transferable
                    .isDataFlavorSupported(TransferableEntrySelection.flavorInternal)) {
                final AbstractGroup group = target.getGroup();
                if (!group.supportsAdd()) {
                    // this should never happen, because the same condition
                    // is checked in dragOver already
                    dtde.rejectDrop();
                    return;
                }
                final TransferableEntrySelection selection = (TransferableEntrySelection) transferable
                        .getTransferData(TransferableEntrySelection.flavorInternal);
                final BibtexEntry[] entries = selection.getSelection();
                int assignedEntries = 0;
                for (int i = 0; i < entries.length; ++i) {
                    if (!target.getGroup().contains(entries[i]))
                        ++assignedEntries;
                }

                // warn if assignment has undesired side effects (modifies a
                // field != keywords)
                if (!Util.warnAssignmentSideEffects(group, 
                        selection.getSelection(), 
                        groupSelector.getActiveBasePanel().getDatabase(),
                        groupSelector.frame))
                    return; // user aborted operation
                
                AbstractUndoableEdit undo = group.add(selection.getSelection());
                if (undo instanceof UndoableChangeAssignment)
                    ((UndoableChangeAssignment) undo).setEditedNode(target);
                dtde.getDropTargetContext().dropComplete(true);
                groupSelector.revalidateGroups();
                groupSelector.concludeAssignment(undo, target, assignedEntries);
            } else {
                dtde.rejectDrop();
                return;
            }
        } catch (IOException ioe) {
            // ignore
        } catch (UnsupportedFlavorException e) {
            // ignore
        }
    }

    public void dragExit(DropTargetEvent dte) {
        setHighlight1Cell(null);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        GroupTreeNode selectedNode = getSelectedNode();
        if (selectedNode == null)
            return; // nothing to transfer (select manually?)
        Cursor cursor = DragSource.DefaultMoveDrop;
        dragNode = selectedNode;
        dge.getDragSource().startDrag(dge, cursor, selectedNode, this);
    }

    /** Returns the first selected node, or null if nothing is selected. */
    public GroupTreeNode getSelectedNode() {
        TreePath selectionPath = getSelectionPath();
        return selectionPath != null ? (GroupTreeNode) selectionPath
                .getLastPathComponent() : null;
    }

    /**
     * Refresh paths that may have become invalid due to node movements within
     * the tree. This method creates new paths to the last path components
     * (which must still exist) of the specified paths.
     * 
     * @param paths
     *            Paths that may have become invalid.
     * @return Refreshed paths that are all valid.
     */
    public Enumeration refreshPaths(Enumeration paths) {
        Vector freshPaths = new Vector();
        while (paths.hasMoreElements()) {
            freshPaths.add(new TreePath(
                    ((DefaultMutableTreeNode) ((TreePath) paths.nextElement())
                            .getLastPathComponent()).getPath()));
        }
        return freshPaths.elements();
    }

    public void treeCollapsed(TreeExpansionEvent event) {
        ((GroupTreeNode)event.getPath().getLastPathComponent()).isExpanded = false;
    }

    public void treeExpanded(TreeExpansionEvent event) {
        ((GroupTreeNode)event.getPath().getLastPathComponent()).isExpanded = true;
    }
    
    /** Highlights the specified cell or disables highlight if cell == null */
    public void setHighlight1Cell(Object cell) {
        cellRenderer.setHighlight1Cell(cell);
        repaint();
    }
    
    /** Highlights the specified cells or disables highlight if cells == null */
    public void setHighlight2Cells(Object[] cells) {
        cellRenderer.setHighlight2Cells(cells);
        repaint();
    }
}
