<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="org.owasp.webgoat.session.*, org.owasp.webgoat.lessons.DBCrossSiteScripting.DBCrossSiteScripting" 
	errorPage="" %>
<style>
<jsp:include page="DBCrossSiteScripting.css" />
</style>
<%
WebSession webSession = ((WebSession)session.getAttribute("websession"));
DBCrossSiteScripting currentLesson = (DBCrossSiteScripting) webSession.getCurrentLesson();
%>
<div id="lesson_wrapper">
	<div id="lesson_header"></div>
	<div class="lesson_workspace">
	<%
	String subViewPage = currentLesson.getPage(webSession);
	if (subViewPage != null)
	{
		//System.out.println("Including sub view page: " + subViewPage);
	%>
	<jsp:include page="<%=subViewPage%>" />
	<%
	}
	%>

	</div>
</div>