<?php
/**
** Emy Itegbe
** CMPE 207
** 008740953
**
*/
include 'db.php';
$con = mysqli_connect($host, $username, $password, $database);
$file_name = $_POST["name"];
if(isset($_POST["name"])){
    $name = $_POST["name"];
    $result = mysqli_query($con, "SELECT * FROM Files WHERE        
     file_name='$name'");
    $row = mysqli_fetch_array($result);
    $url = $row['url'];
    unlink($url);
    $query = "DELETE FROM Files WHERE file_name='$name';";
    mysqli_query($con,$query);
}
mysqli_close($con);
//redirect
$host  = $_SERVER['HTTP_HOST'];
$uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
$extra = 'webclient.php';
header("Location: http://$host$uri/$extra");
exit;




