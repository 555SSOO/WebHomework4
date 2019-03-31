<!DOCTYPE html>
<html lang="en">
<head>
    <title>WebHW4</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div id="page-wrap">

    <h1>Please input the assistant name and the grade</h1>
    <div id="contact-area">

        <form method="post" action="http://localhost:8080/grade">
            <label for="assistant_name">Assistant name:</label>
            <input type="text" name="assistant_name" id="assistant_name"/>

            <label for="grade">Grade:</label>
            <input type="text" name="grade" id="grade"/>

            <input type="submit" name="submit" value="Submit" class="submit-button"/>
        </form>

    </div>

    <div id="request-get">

        <form method="get" action="http://localhost:8080/grade">
            <input type="submit" name="get-values" value="Get Avg Grades" class="get-button"/>
        </form>

    </div>


</div>
</body>
</html>