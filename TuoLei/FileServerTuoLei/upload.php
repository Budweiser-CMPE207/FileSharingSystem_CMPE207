<?php
/**
 * Created by PhpStorm.
 * User: Tuo Lei
 * Date: 11/7/14
 * Time: 10:31 PM
 */

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

    /* curl example! */
//    $headers = array("Content-Type:multipart/form-data"); // cURL headers for file uploading
//    $postfields = array("file" => "@".realpath($url), "user" => "Replicated", "key" => "Budweiser");
//    $ch = curl_init();
//    $options = array(
//        CURLOPT_URL => "http://localhost:3000/endpoint.php",
//        CURLOPT_HEADER => true,
//        CURLOPT_POST => 1,
//        CURLOPT_HTTPHEADER => $headers,
//        CURLOPT_POSTFIELDS => $postfields,
//        CURLOPT_INFILESIZE => $size,
//        CURLOPT_RETURNTRANSFER => true
//    ); // cURL options
//    curl_setopt_array($ch, $options);
//    curl_exec($ch);
//    if(!curl_errno($ch))
//    {
//        $info = curl_getinfo($ch);
//        if ($info['http_code'] == 200)
//            $errmsg = "File uploaded successfully";
//    }
//    else
//    {
//        $errmsg = curl_error($ch);
//    }
//    curl_close($ch);
//    echo $errmsg;
//    exit;

    //redirect
    $host  = $_SERVER['HTTP_HOST'];
    $uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
    $extra = 'webclient.php';

    header("Location: http://$host$uri/$extra");
    exit;
}
