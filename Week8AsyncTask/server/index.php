<?php

    $a = $_GET['a'];
    $b = $_GET['b'];

    $res = $a * $b;
    
    $data = [ 'result' => $res];

    sleep(10);

    echo json_encode( $data );

?>