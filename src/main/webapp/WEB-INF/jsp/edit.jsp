<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim Vanny
  Date: 6/7/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <jsp:include page="fragments/link.jsp"/>
    <title>EDIT</title>
</head>
<body>
<section>
    <div class="container" style="width: 800px">
        <div class="text-center" style="font-weight: bold">
            Редактирование.
        </div>
        <br>
        <jsp:include page="fragments/back.jsp"/>
        <div class="row">
            <div class="float-right" style="width: 250px;">
                <form method="POST" action="<c:url value="/controller"/>" id="update">
                    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
                    <input type="hidden" name="id" value="${meal.id}"/>
                    <input type="hidden" name="action" value="update"/>
                    <div class="form-group">
                        <label class="sr-only" for="dates">Дата</label>
                        <input id="dates" type="datetime-local" name="dates" value="${meal.dateTime}">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="desc">Описание</label>
                        <textarea class="form-control" id="desc" name="desc" rows="10" cols="1"
                                  placeholder="описание" required>${meal.description}
                        </textarea>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="calories">Калории</label>
                        <input class="form-control" value="${meal.calories}" id="calories" name="calories"
                               placeholder="калории" required type="text">
                    </div>
                    <div class="form-group">
                        <button class="btn" style="background-color:#DC3545; font-weight: bold; width: 250px"
                                type="submit" form="update">
                            сохранить
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
