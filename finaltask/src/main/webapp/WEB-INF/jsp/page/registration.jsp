<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container-fluid">
    <div class="row">
        <div class="col-4"></div>

        <div class="col-4">

            <form method="post" action="/final/registr.html">
                <div class="card mt-5 mb-5" style="background-color: #9999CC">
                    <form class="ml-3 mr-3">
                        <div class="form-group m-2">
                            <label for="inputEmail">Логин:</label>
                            <input type="text" name="login" class="form-control " id="inputEmail"
                                   placeholder="Введите логин" required>
                        </div>
                        <div class="form-group m-2">
                            <label for="inputPassword">Пароль:</label>
                            <input type="password" name="password" class="form-control is-invalid" id="inputPassword"
                                   placeholder="Введите пароль" required>
                            <c:if test="${sessionScope.incorrect != null}">
                                <div class="invalid-feedback">${sessionScope.incorrect}</div>
                            </c:if>
                        </div>

                        <button type="submit" class="btn btn-primary m-2">Зарегистрироваться</button>

                    </form>
                </div>
            </form>


        </div>


        <div class="col-4"></div>


    </div>
</div>
