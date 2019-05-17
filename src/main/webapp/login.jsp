
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Giriş</title>
</head>
<body>

<form method="post" action="loadLogin.ajax">
    <fieldset>
        <legend>Kullanici Girisi</legend>
        Isim: <input type="text" name="adi" /><br />
        Sifre: <input type="password" name="sifre" /><br />

    </fieldset>

    <input type="submit" value="Kaydet" />
</form>
</body>
</html>
