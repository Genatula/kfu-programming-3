<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Sign up</title>
</head>
<body>
<div class="form">
    <form method="post" action="/main/signin">
        <input type="text" name="email" id="email" placeholder="Email">
        <br><br>
        <c:if test="${userDoesntExist}">
            <span style="color:red;">User with such email doesn't exist</span>
            <br><br>
        </c:if>
        <input type="password" name="password" id="password" placeholder="Password">
        <br><br>
        <c:if test="${incorrectCredentials}">
            <span style="color:red;">Entered password is incorrect</span>
            <br><br>
        </c:if>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
<%@ include file="_footer.jsp"%>
