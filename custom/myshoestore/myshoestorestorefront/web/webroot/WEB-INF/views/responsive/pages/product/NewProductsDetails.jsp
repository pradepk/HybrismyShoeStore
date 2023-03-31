<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

<template:page pageTitle="${pageTitle}">
<c:if test="${not empty  newProductsData}">
<table>
   <tr>
    <th><spring:theme code="myshoestore.newproducts.code"/></th>
    <th><spring:theme code="myshoestore.newproducts.name"/></th>
   <th><spring:theme code="myshoestore.newproducts.description"/></th>
   <th><spring:theme code="myshoestore.newproducts.size"/></th>
   <th><spring:theme code="myshoestore.newproducts.color"/></th>
   <th><spring:theme code="myshoestore.newproducts.purchaseDate"/></th>
   <th><spring:theme code="myshoestore.newproducts.warranty"/></th>

           </tr>
<c:forEach items="${newProductsData}" var="newProductsData">
<tr>

<td>${newProductsData.code}</td>
<td>${newProductsData.name}</td>
<td>${newProductsData.description}</td>
<td>${newProductsData.size}</td>
<td>${newProductsData.color}</td>
<td>${newProductsData.purchaseDate}</td>
<td>${newProductsData.warranty}</td>
</tr>
<tr><td>
     <form:form action="removeNewProducts/${newProductsData.name}" method="post">
     <button type="submit" class="btn btn-danger btn-block">
     <spring:theme code="Delete" text="Delete"/>
     </button>
     </form:form>
     </tr></td>
</c:forEach>
</table>
</c:if>
</template:page>