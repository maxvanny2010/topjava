<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim Vanny
  Date: 6/7/2020
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <jsp:include page="fragments/link.jsp"/>
    <title>INDEX</title>
</head>
<body>
<div class="container">
    <div class="text-center" style="font-weight: bold">Таблица приемов пищи(лимит 1500)</div>
    <br>
    <div class="row"><a href="<c:url value="/add"/>">Добавить</a></div>
    <div class="row">
        <table class="table table-condensed">
            <thead class="p-1 text-center">
            <tr>
                <jsp:useBean id="hats" scope="request" type="java.util.List"/>
                <c:forEach var="hat" items="${hats}">
                    <jsp:useBean id="hat" type="java.lang.String"/>
                    <th>
                        <c:if test="${hat eq 'редактор'}">
                            <div><i class="fas fa-cloud" style="color:dodgerblue;"></i></div>
                        </c:if>
                        <c:if test="${hat eq 'удалить'}">
                            <div><i class="fas fa-trash" style="color:dodgerblue;"></i></div>
                        </c:if>
                        <c:if test="${hat ne 'редактор' && hat ne 'удалить'}">
                            <c:out value="${hat}"/>
                        </c:if>
                    </th>
                </c:forEach>
            </tr>
            </thead>
            <tbody class="p-1 text-center bg-white" id="list">
            <jsp:useBean id="list" scope="request" type="java.util.List"/>
            <c:forEach var="meal" items="${list}">
                <c:if test="${meal.excess eq 'true'}">
                    <tr style="color: red">
                </c:if>
                <c:if test="${meal.excess eq 'false'}">
                    <tr style="color: green">
                </c:if>
                <td id="times">
                    <c:if test="${meal.dateTime ne null}">
                        <c:out value="${fn:split(meal.dateTime,'T')[0]}"/>
                        <c:out value="${fn:split(meal.dateTime,'T')[1]}"/>
                    </c:if>
                </td>
                <td>
                    <c:out value="${meal.description}"/>
                </td>
                <td>
                    <c:out value="${meal.calories}"/>
                </td>
                <td>
                    <form action="<c:url value="/controller"/>" method="POST" title="редактировать">
                        <input name="action" type="hidden" value="edit"/>
                        <input name="id" id="ed" type="hidden" value="${meal.id}"/>
                        <input class="btn btn-info btn-sm"
                               style="background-color:dodgerblue; border-color:white; color: white;
                                   border-radius: 0;"
                               type="submit" value="обновить"/>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/controller"/>" method="POST" id="delete" title="удалить">
                        <input name="action" type="hidden" value="delete"/>
                        <input name="id" id="del" type="hidden" value="${meal.id}"/>
                        <input class="btn btn-info btn-sm"
                               style="background-color:dodgerblue; border-color:white; color: white;
                                   border-radius: 0;"
                               type="submit" value="удалить"/>
                    </form>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
