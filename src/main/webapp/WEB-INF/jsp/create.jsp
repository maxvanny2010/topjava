<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim Vanny
  Date: 6/7/2020
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <jsp:include page="fragments/link.jsp"/>
    <title>CREATE</title>
</head>
<body>
<div class="container" style="width: 800px;">
    <div class="text-center" style="font-weight: bold">
        Добавление.
    </div>
    <br>
    <jsp:include page="fragments/back.jsp"/>
    <div class="row">
        <div class="container">
            <div class="float-left" style="width: 250px;">
                <form action="<c:url value="/controller"/>" id="create" method="post">
                    <input type="hidden" name="action" value="create">
                    <div class="form-group">
                        <label class="sr-only" for="dates">Дата</label>
                        <input id="dates" type="datetime-local" name="dates">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="desc">Описание</label>
                        <textarea class="form-control" id="desc" name="desc" rows="10" cols="1"
                                  placeholder="описание" required></textarea>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="calories">Калории</label>
                        <input class="form-control" id="calories" name="calories" placeholder="калории" required
                               type="text"/>
                    </div>
                    <div class="form-group">
                        <input class="btn" style="background-color:#DC3545; font-weight: bold; width: 250px"
                               type="submit" form="create"
                               value="сохранить"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
