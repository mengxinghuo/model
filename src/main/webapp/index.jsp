<html >

<body>
<h2>Hello World!</h2>

SpringMVC上传文件
<form name="form1" action="/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="SpringMVC上传文件">
</form>
富文本文件上传
<form name="form1" action="/product/image_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="富文本文件上传">
</form>
</body>
</html>
