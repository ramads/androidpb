<?php

    $a = $_GET['a'];
    $b = $_GET['b'];

    $res = $a * $b;

    sleep(5);
    
    $data = [ 'result' => $res];
    
    echo json_encode( $data );

?>