<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Sign up</title>
</head>
<body>
    <div class="form">
        <form method="post" action="/main/signup">
            <input type="text" name="username" id="username" placeholder="Username">
            <br><br>
            <input type="text" name="email" id="email" placeholder="Email">
            <br><br>
            <c:if test="${isEmailInvalid}">
                <span style="color:red;">Email format is not correct</span>
                <br><br>
            </c:if>
            <input type="password" name="password" id="password" placeholder="Password">
            <br><br>
            <c:if test="${isPasswordInvalid}">
                <span style="color:red;">Password format is not correct</span>
                <br><br>
            </c:if>
            <c:if test="${areFieldsEmpty}">
                <span style="color: red;">Please fill in all fields</span>
                <br><br>
            </c:if>
            <c:if test="${userAlreadyExists}">
                <span style="color:red;">User already exists. Want to sign in?</span>
                <br><br>
            </c:if>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
<%@ include file="_footer.jsp"%>
