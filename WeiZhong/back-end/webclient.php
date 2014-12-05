<!DOCTYPE html>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <script>
        function showForm(){
            document.getElementById("addUser").style.display="block";
        }
    </script>
</head>
<body>

<h1>Files Viewer</h1>

<h3>CMPE207 Project, Team:Budweiser</h3>
<button class="btn btn-success" style="margin-left: 10px" onclick="showForm()">Add a File</button>

<form action="" method="get" class="form-inline" style="display: inline-block;margin-left: 10px">
    <input type="text" class="form-control" name="query" value="Wei Zhong">
    <button type="submit" class="btn btn-primary">Search</button>
</form>
<div id="addUser" style="margin: 10px; display: none">
    <form action="upload.php" method="post" class="form-inline" role="form" enctype="multipart/form-data">
        <input type="hidden" name="user" value="Wei Zhong" />
        <div class="form-group">
            <label for="first_name">Upload File</label>
            <input type="file" class="form-control" id="file" name="file">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

<?php
include 'db.php';

//connect to database
$con = mysqli_connect($host, $username, $password, $database);
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL DB: " . mysqli_connect_error();
}

if(isset($_GET["query"])){
    $query = $_GET["query"];
    $result = mysqli_query($con, "SELECT * FROM Files WHERE Files.user_name LIKE '%$query%'
      or Files.file_name LIKE '%$query%' or file_type LIKE '%$query%'");
}
else{
    $result = mysqli_query($con, "SELECT * FROM Files ORDER BY idFiles DESC;");
}


echo "<table class='table table-striped table-bordered' style='margin-top: 5px'><tr>
        <th>ID</th>
        <th>File Name</th>
        <th>Original Server</th>
        <th>File Size</th>
        <th>File Type</th>
        <th>Action</th>
      </tr>";
while ($row = mysqli_fetch_array($result)) {
    echo "<tr>";
    echo "<td>" . $row['idFiles'] . "</td><td>"
        ."<a href='".$row['url']."'>". $row['file_name'] . "</a></td><td>"
        . $row['user_name'] . "</td><td>"
        . ceil($row['file_size']/1024) . "kb</td><td>"
        . $row['file_type'] . "</td><td>"
        . "<form action='delete.php' method='post' class='form-inline' role='form'>
                <input type='hidden' name='id' value='".$row['idFiles']."' />
                <button type='submit' class='btn btn-danger'>DELETE</button>
            </form></td>";
    echo "</tr>";
}
echo "</table>";

mysqli_close($con);
?>

</body>
</html>
