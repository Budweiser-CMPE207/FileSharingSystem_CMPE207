<?php
/*
** Created by Emy Itegbe
** CMPE 207
** 008740953
*/
$user_name = $_POST['username'];
$passwd = $_POST['password'];
include 'db.php';
//connect to database
$con = mysqli_connect($host, $username, $password, $database);
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL DB: " . mysqli_connect_error();
}
$result = mysqli_query($con, "select * from User where username='$user_name' and password='$passwd' ");

if($row = mysqli_fetch_array($result)){
    echo 'Success_user';
} else {
	echo "Fail";
}
exit;

