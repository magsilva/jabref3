    <div id="header">
      <img alt="JabRef title" src="/images/title2.png" />
    </div>

<?php 

$fullpath = split('/', $HTTP_SERVER_VARS["SCRIPT_FILENAME"]);
$filename = $fullpath[count($fullpath)-1]; 

function navigate($URL, $name, $shortname, $file) {

  if($file==$URL) {
    $URL="#";
  }

  echo "        <li><a href=\"",$URL,"\" title=\"",$name,"\">",$shortname,"</a></li>\n";

}

echo"    <div id=\"navcontainer\">
      <ul id=\"navlist\">
";  

    navigate("/index.php","Welcome page","Overview",$filename);
    navigate("/faq.php","Frequently Asked Questions","FAQ",$filename);
    navigate("/documentation.php","Documentation.php","Documentation",$filename);
    navigate("/resources.php","Download JabRef resources: journal abbreviation lists and export filters","Resources",$filename);
    navigate("/screenshots.php","Some Screenshots","Screenshots",$filename);
    navigate("/revisionhistory.php","History and documentation about JabRef releases","History",$filename);
    navigate("/contact.php","Contact the JabRef people","Contact",$filename);
    navigate("/download.php","The JabRef Download page at Sourceforge","Download",$filename);

echo"      </ul>
    </div>
";  

?>
