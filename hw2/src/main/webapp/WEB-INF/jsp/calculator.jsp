<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Calculator</title>
</head>
<body>
    <div class="message">
        <c:choose>
            <c:when test="${!isValid}">
                <h1>Some of the arguments are missing</h1>
            </c:when>
            <c:when test="${!isDividerValid}">
                <h1>Division on zero is not allowed</h1>
            </c:when>
            <c:when test="${!isNumber}">
                <h1>You must provide numbers for divisions</h1>
            </c:when>
            <c:when test="${welcome}">
                <h1>Welcome to the calculator app!</h1>
            </c:when>
            <c:otherwise>
                <h1>${result}</h1>
            </c:otherwise>
        </c:choose>
    </div>
</body>
<%@ include file="_footer.jsp"%>
