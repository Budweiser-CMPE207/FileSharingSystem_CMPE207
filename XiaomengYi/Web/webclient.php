<!DOCTYPE html>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.4/css/jquery.dataTables.css">
    <script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>

    <script type="text/javascript" class="init">
        $(document).ready(function() {
            $('#example').DataTable();
        } );
    </script>

    <script>
        function showForm(){
            document.getElementById("addUser").style.display="block";
        }
    </script>

    <style type="text/css">
    .data {
        padding: 20px;
    }
    </style>
</head>
<body>
<div class="jumbotron">
  <h1>CMPE207 Project  Team:Budweiser</h1>
  <p>This is a Distributed File Sharing Client.You can upload file to different servers.</p>
  <p><a class="btn btn-primary btn-lg" href="http://www.openloop.com/education/classes/sjsu_engr/engr_prog_network/fall2014/index.php#writingproject" role="button">Learn more</a></p>
</div>
<div style="margin-left: 10px;float:left;padding-top:10px;margin-bottom:20px;">
<button class="btn btn-success" style="margin-left: 10px;" onclick="showForm()">Add a File</button>
</div>
<!--<form action="" method="get" class="form-inline" style="display: inline-block;margin-left: 10px">
    <input type="text" class="form-control" name="query" value="Xiaomeng Yi">
    <button type="submit" class="btn btn-primary">Search</button>
</form>-->

<div id="addUser" style="margin: 10px; display: none; float:left;">
    <form action="upload.php"  method="post" class="form-inline" role="form" enctype="multipart/form-data">
        <input type="hidden" name="user" value="Xiaomeng Yi" />
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


/*echo "<table class='table table-hover table-striped table-bordered' style='margin-top: 5px'><tr class = 'info'>
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
echo "</table>";*/

echo "<div class='data'><table id='example' class='display table table-hover table-striped table-bordered' cellspacing='0' width='100%'>
      <thead> 
        <tr>
            <th>ID</th>
            <th>File Name</th>
            <th>Original Server</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Action</th>
        </tr>
      </thead>";
echo "<tbody>";    
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
echo "</tbody> </table> </div>"; 


mysqli_close($con);
?>
