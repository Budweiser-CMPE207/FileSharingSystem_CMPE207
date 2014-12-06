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

while ($row = mysqli_fetch_array($result)) {
    echo $row['idFiles'] . ":" 
        . $row['file_name'] . ":"
        . $row['user_name'] . ":"
        . ceil($row['file_size']/1024) . ":"
        . $row['file_type']. "\n";
}

mysqli_close($con);
echo 'Success!!';
exit;