<?php
/**
 * Created by Emy Itegbe
 * CMPE 207
 * 008740953
 * 
 */
$key = $_POST["key"];
if($key!='Budweiser'){
    echo 'Wrong Key!';
    exit;
}

if ($_FILES["file"]["error"] > 0) {
    echo "Return Code: " . $_FILES["file"]["error"] . "<br />";
}
else {
    $file_name = $_POST["file_name"];
    if($file_name==''){
        $file_name = $_FILES["file"]["name"];
    }
    $user = $_POST["user"];
    if($user==''){
        $user = 'Anonymous';
    }
    $type = $_FILES["file"]["type"];
    $size = $_FILES["file"]["size"];
    $id = uniqid();

    //ext name
    $temp_arr = explode(".", $_FILES["file"]["name"]);
    $file_ext = array_pop($temp_arr);
    $file_ext = trim($file_ext);
    $file_ext = strtolower($file_ext);
    if($file_ext=='php'){
        echo "<h1>There's no need to inject a script to the server!";
        exit;
    }

    $new_name = $id.".".$file_ext;
    $url = "upload/".$new_name;

    include 'db.php';

    //connect to database
    $con = mysqli_connect($host, $username, $password, $database);
    if (mysqli_connect_errno()) {
        echo "Failed to connect to MySQL DB: " . mysqli_connect_error();
        exit;
    }
    $query = "INSERT INTO Files (file_name, user_name,file_size, file_type, new_name, url)
         VALUES ('$file_name', '$user', '$size','$type','$new_name','$url');";
    mysqli_query($con,$query);

    mysqli_close($con);
    move_uploaded_file($_FILES["file"]["tmp_name"], $url);

    echo 'Success!!';
    exit;
}

