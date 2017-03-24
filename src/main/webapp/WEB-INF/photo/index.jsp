<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
</head>

<body>

<form method="POST" action="upload" enctype="multipart/form-data" >

    File:
    <input type="file" name="photo" /> <br/>

    <input type="submit" value="Upload" name="upload" id="upload" />
</form>
</body>
</html>
