<?php

$type = $_GET['item_type'];
 $type = "barang";
 if($type == "barang"){
	
	require_once 'connect.php';
	
	$sql = "SELECT * FROM barang";
	
	$r = mysqli_query($conn,$sql);
	
	$result = array();

	while ($row = mysqli_fetch_assoc($r)) {
		array_push($result,
             array(
                 'bid'=>$row['bid'],
                 'bnama'=>$row['bnama'],
                 'bsatuan'=>$row['bsatuan'],
                 'bharga_pokok'=>$row['bharga_pokok'],
                 'bharga_jual'=>$row['bharga_jual'],
                 'bharga_jual_grosir'=>$row['bharga_jual_grosir'],
                 'bstok'=>$row['bstok'],
                 'btgl_masuk'=>$row['btgl_masuk'],
                 'bkategori_id'=>$row['bkategori_id']
             ));
	}
	
	echo json_encode($result, TRUE);
}
	mysqli_close($conn);