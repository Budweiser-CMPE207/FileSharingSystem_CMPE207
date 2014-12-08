<?php

$user_name = $_POST['username'];
$passwd = $_POST['password'];

include 'db.php';

//connect to database
$con = mysqli_connect($host, $username, $password, $database);
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL DB: " . mysqli_connect_error();
}

$result = mysqli_query($con, "select * from users where username='$user_name' and password='$passwd' limit 1");
if($row = mysqli_fetch_array($result)){
	$id = uniqid();
	
    mysqli_query($con, "update users set uuid = '$id' where username='$user_name' and password='$passwd'");
	echo 'Success_user'. ":" .$id;
} else {
	echo "Fail";
}



exit;
