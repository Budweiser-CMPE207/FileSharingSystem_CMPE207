<?php
/**
 * Created by Emy Itegbe.
 * Cmpe 207
 * 008740953
 * Delete File
 **/
include 'db.php';
$con = mysqli_connect($host, $username, $password, $database);

$file_name = $_POST["id"];
if(isset($_POST["id"])){
    $id = $_POST["id"];
    $result = mysqli_query($con, "SELECT * FROM Files WHERE idFiles='$id'");
    $row = mysqli_fetch_array($result);
    $url = $row['url'];
    unlink($url);

    $query = "DELETE FROM Files WHERE idFiles='$id';";
    mysqli_query($con,$query);
}

mysqli_close($con);

//redirect
$host  = $_SERVER['HTTP_HOST'];
$uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
$extra = 'webclient.php';

header("Location: http://$host$uri/$extra");
exit;

