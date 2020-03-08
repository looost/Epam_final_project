<%--
  Created by IntelliJ IDEA.
  User: Misha
  Date: 09.02.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="post">
        <div class="form-group">
            <label for="exampleFormControlInput1">Название сериала:</label>
            <input type="text" name="name" class="form-control" id="exampleFormControlInput1"
                   placeholder="Название сериала">
        </div>
        <div class="form-group">
            <label for="description">Описание сериала</label>
            <textarea class="form-control" name="description" id="description" rows="3"></textarea>
        </div>
        <div class="form-group">
            <label for="logo">Ссылка на лого</label>
            <input type="text" name="logo" class="form-control" id="logo"
                   placeholder="Ссылка на лого">
        </div>
        <div class="form-group">
            <label for="full_logo">Ссылка на полное лого</label>
            <input type="text" name="full_logo" class="form-control" id="full_logo"
                   placeholder="Ссылка на полное лого">
        </div>
        <div class="form-group">
            <label for="date">Дата выхода сериала</label>
            <input type="text" name="release_date" class="form-control" id="date"
                   placeholder="Дата выхода сериала">
        </div>
        <div class="form-group">
            <label for="country_id">ID страны</label>
            <input type="text" name="country_id" class="form-control" id="country_id"
                   placeholder="id страны">
        </div>
        <div class="form-group">
            <label for="studio_id">ID студии</label>
            <input type="text" name="studio_id" class="form-control" id="studio_id"
                   placeholder="ID студии">
        </div>


        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>