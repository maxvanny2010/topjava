<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim Vanny
  Date: 6/7/2020
  Time: 11:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="fragments/link.jsp"/>
    <title>404</title>
</head>
<body>
<div class="container" style="width: 800px">
    <div class="text-center" style="font-weight: bold">
        Страница 404.
    </div>
    <br>
    <div class="row">
        <div class="d-flex justify-content-end" style="width: 800px">
            <jsp:include page="fragments/back.jsp"/>
        </div>
        <div class=row>
            <div class="d-flex justify-content-center">
                Мне очень жаль, что то пошло не так как было мной задумано...
            </div>
        </div>
    </div>
</div>
</body>
</html>
