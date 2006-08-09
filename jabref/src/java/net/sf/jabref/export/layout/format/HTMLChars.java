package net.sf.jabref.export.layout.format;

import net.sf.jabref.export.layout.*;
import net.sf.jabref.Globals;

public class HTMLChars implements LayoutFormatter {


  public String format(String field) {
    int i;
    field = firstFormat(field);

    StringBuffer sb = new StringBuffer("");
    StringBuffer currentCommand = null;
    char c;
    boolean escaped = false, incommand = false;
    for (i=0; i<field.length(); i++) {
      c = field.charAt(i);
      if (escaped && (c == '\\')) {
        sb.append('\\');
        escaped = false;
      }
      else if (c == '\\') {
        escaped = true;
        incommand = true;
        currentCommand = new StringBuffer();
      }
      else if (!incommand && (c=='{' || c=='}')) {
        // Swallow the brace.
      }
      else if (Character.isLetter((char)c) ||
               (Globals.SPECIAL_COMMAND_CHARS.indexOf(""+(char)c) >= 0)) {
        escaped = false;
        if (!incommand)
          sb.append((char)c);
          // Else we are in a command, and should not keep the letter.
        else {
          currentCommand.append( (char) c);

          testCharCom: if ((currentCommand.length() == 1)
              && (Globals.SPECIAL_COMMAND_CHARS.indexOf(currentCommand.toString()) >= 0)) {
            // This indicates that we are in a command of the type \^o or \~{n}
            if (i >= field.length()-1)
              break testCharCom;

            String command = currentCommand.toString();
            i++;
            c = field.charAt(i);
            //System.out.println("next: "+(char)c);
            String combody;
            if (c == '{') {
              IntAndString part = getPart(field, i, false);
              i += part.i;
              combody = part.s;
            }
            else {
              combody = field.substring(i,i+1);
              //System.out.println("... "+combody);
            }
            Object result = Globals.HTMLCHARS.get(command+combody);
	    
            if (result != null)
              sb.append((String)result);

            incommand = false;
            escaped = false;

          }

        }

      }
      else {
        String argument = null;

        if (!incommand) {
          sb.append((char)c);
        }
        else if (Character.isWhitespace(c) || c == '{') {
          // First test if we are already at the end of the string.
          //if (i >= field.length()-1)
          //  break testContent;

          String command = currentCommand.toString();
          // Then test if we are dealing with a italics or bold command.
          // If so, handle.
          if (command.equals("emph") || command.equals("textit")) {
            IntAndString part = getPart(field, i, true);
              
            i += part.i;
              sb.append("<em>").append(part.s).append("</em>");
          }
          else if (command.equals("textbf")) {
            IntAndString part = getPart(field, i, true);
            i += part.i;
              sb.append("<b>").append(part.s).append("</b>");
          }
          else {
            IntAndString part = getPart(field, i, true);
            i += part.i; argument = part.s;
          }
        }
        else if (c == '}') {
          argument = "";
        }
        else {
            /* TODO: this point is reached, apparently, if a command is terminated in a
             * strange way, such as with "$\omega$". Also, the command "\&" causes us
             * to get here. The former issue is maybe a little difficult to address, since
             * it involves the LaTeX math mode. We don't have a complete LaTeX parser, so
             * maybe it's better to ignore these commands?
            */
          //System.err.println("Unreachable code?? '"+field+"'");
        }
        if (argument != null) {
          // handle common case of general latex command
          String command  = currentCommand.toString();
          Object result = Globals.HTMLCHARS.get(command+argument);
          //System.out.print("command: "+command+", arg: "+argument);
          //System.out.print(", result: ");
          // If found, then use translated version. If not, then keep the
          // text of the parameter intact.
          if (result != null) {
            //System.out.println((String)result);
            sb.append((String)result);
          } else {
            //System.out.println(argument);
            sb.append(argument);
          }
        }
        incommand = false;
        escaped = false;
      }
    }

    return sb.toString();
        //field.replaceAll("\\\\emph", "").replaceAll("\\\\em", "").replaceAll("\\\\textbf", "");
  }

  private String firstFormat(String s) {
    return s.replaceAll("&|\\\\&","&amp;").replaceAll("[\\n]{1,}","<p>");//.replaceAll("--", "&mdash;");
  }

  private IntAndString getPart(String text, int i, boolean terminateOnEndBraceOnly) {
    char c;
    int count = 0;//, i=index;
    StringBuffer part = new StringBuffer();
    // advance to first char and skip wihitespace
    i++;
    for ( ; i < text.length() ; ++i) {
      if (!Character.isWhitespace(text.charAt(i)))
        break;
    }
    // then grab whathever is the first token (counting braces)
    for ( ; i < text.length() ; ++i) {
      c = text.charAt(i);
      if (!terminateOnEndBraceOnly && count == 0 && Character.isWhitespace(c)) {
        i--; // end argument and leave whitespace for further processing
        break;
      }
      if (c == '}' && --count < 0)
        break;
      else if (c == '{')
        count++;
      part.append((char)c);
    }
    //System.out.println("part: "+part.toString()+"\nformatted: "+format(part.toString()));
    return new IntAndString(part.length(), format(part.toString()));
  }

  private class IntAndString{
    public int i;
    String s;
    public IntAndString(int i, String s) {
      this.i = i;
      this.s = s;
    }
  }
}
