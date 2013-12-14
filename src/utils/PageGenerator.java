package utils;


import java.io.FileNotFoundException;
import java.io.IOException;



public class PageGenerator {
	private String htmlCode;
	private final String userName = "";
	public PageGenerator(){
		//System.out.println("PageGenerator создан");
		//userName = "";
		
	}
	public void waitAuthenfication (){
		htmlCode =  "<head>"+
				
				"</head>"+
				"<body"+
				"<body>";
	}
	public void pageReload2 ( ){
		htmlCode = "<html>"+
		"<head>"+
		"<script type='text/JavaScript'>"+
		"<!--"+
		"function timedRefresh(timeoutPeriod) {"+
			"setTimeout('location.reload(true);',timeoutPeriod);"+
		"}"+
		//   -->
		"</script>"+
		"</head>"+
		"<body onload='JavaScript:timedRefresh(5000);'>"+
		"</body>"+
		
		"</html>";
	}
	
	public void pageReload (int sessionId){
		htmlCode = "<html>"+

					"<head>"
			
					+"<script language='JavaScript'>"
					+"var sURL = unescape(window.location.pathname);"

					+"function doLoad()"
					+"{"
					
					+"}"

					+"function refresh(){"
					+"if(!document.getElementById('text').value.replace(/s+/g, '').length) {"
					+  "  window.location.href = sURL;"

					+"}"
					+"}"
					+"</script>"

					+"<script language='JavaScript1.1'>"
					+"function refresh()"
					+"{"
					+"if(!document.getElementById('text').value.replace(/s+/g, '').length) {"
					+"window.location.replace( sURL );"
					+"}"
					+"}"
					+"function setFocus() {"
					+"document.getElementById('text').select();"
					+"document.getElementById('text').focus();"
					+"}"
					+"</script>"

					+"<script language='JavaScript1.2'>"
					+"function refresh()"
					+"{"
					+"if(!document.getElementById('text').value.replace(/s+/g, '').length) {"
					+"window.location.reload( false );"
					+"}"
					+"}"
					+"function setFocus() {"
					+"document.getElementById('text').select();"
					+"document.getElementById('text').focus();"
					+"}"
					+"</script>"
					+"</head>"

					+"<body onload='doLoad()'>"

					+"<script language='JavaScript'>"
					+"document.write('<b>' + (new Date).toLocaleString() + '</b>');"

					+"</script>"
					+"<form method=GET>" +
					"<input id='text' type='text' name='userName' value ="+userName+ ">"+
					"<input type='submit' name='Button' value ='send' " +">"+
					"<input type = 'hidden' name='sessionId' value"+"="+"'" + sessionId + "'"+"/>"+
					"</form>"+"</html>";
			//System.out.println(id);
	}
	public void pageReload1(int sessionId) throws FileNotFoundException, IOException{
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<head>");
		builder.append("<noscript>");
		builder.append("<meta http-equiv='refresh' content='2'>");
		builder.append("</noscript>");
		builder.append("<script language='JavaScript'>");
		builder.append("var sURL = unescape(window.location.pathname);");
		builder.append("function doLoad()");
		builder.append("{");
		builder.append("setTimeout( 'refresh()', 1*1000 );");
		builder.append("setFocus();");
		builder.append("var id = getUrlVars()['sessionId'];");
		builder.append("var param = getUrlVars()['param'];");
		builder.append("function getUrlVars() {");
		builder.append("var vars = {};");
		builder.append("var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {");
		builder.append(" vars[key] = value;");
		builder.append("});");
		builder.append("return vars;");
		builder.append("}");
		builder.append("if (id != undefined){");
		builder.append("document.getElementById('ss').value = id;");
		builder.append("}");
		builder.append("if (param != undefined){");
		builder.append("document.getElementById('2').value = param;");
		builder.append("}");
		builder.append("}");
		builder.append("setTimeout( 'refresh()', 0.5*1000 );");
		builder.append("setFocus();");
		builder.append("</script>");
		builder.append("<script language='JavaScript'>");
		builder.append("function refresh()");
		builder.append("{");
		builder.append("if(!document.getElementById('text').value.replace(/s+/g, '').length) {");
		builder.append("window.location.reload( true );");
		builder.append("}");
		builder.append("}");
		builder.append("function setFocus() {");
		builder.append("document.getElementById('text').select();");
		builder.append("document.getElementById('text').focus();");
		builder.append("}");
		builder.append("	</script>");
		builder.append("</head>");
		builder.append("<body onload='doLoad()'>");
		builder.append("<script language='JavaScript'>");
		builder.append("document.write('<b>' + (new Date).toLocaleString() + '</b>');");
		builder.append("	function change_but(){");
		builder.append("var counter = 0;");
		builder.append("for (var i = window.location.toString().length; window.location.toString().charAt(i) != '='; i--) {");
		builder.append("counter = i;");
		builder.append("}");
		builder.append("var url = window.location.toString().substr(0,counter);");
		builder.append("document.getElementById('2').value++;");
		builder.append("url +=   document.getElementById('2').value;");
		builder.append("window.location = url;");
		builder.append("}");
		builder.append("</script>");

		
		builder.append("<form method=GET>");
		builder.append("<input id='text' type='text' name='userName' value =");
		builder.append(userName);
		builder.append(">");
		builder.append("<input type='submit' name='Button' value ='send' ");
		builder.append(">");
		builder.append("<input id = 'ss' type = 'hidden' name='sessionId' value");
		builder.append("=");
		builder.append("'");
		builder.append(sessionId);
		builder.append("'");
		builder.append("/>");
		builder.append("<input type='button' value='Test' onClick='change_but()' id='test_but'><br>");
		builder.append("<input id = '2'  type = 'hidden' name='param' value = '0'  >");
		builder.append("</form>");
	
		builder.append("</html>");
		htmlCode = builder.toString();
		
	}
	public String getPage(){
		return htmlCode;
	}

	public String clearPage(){
		String html = 
				"<html>"+

"<head>"+
				
				"<script language='JavaScript'>"+
		"function openWin() {"+
"myWin= open('', 'displayWindow',"+ 
"'width=400,height=300,status=no,toolbar=no,menubar=no');"+
"}"+
				"</script>"+
				"</head>"+
				"</html>";

		return html;
	}
}
