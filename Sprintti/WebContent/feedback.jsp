<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="tyyli.css" rel="stylesheet" type="text/css">
<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet' type='text/css'>

<!--Import jQuery before materialize.js-->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<title>Palautelomake</title>
</head>
<body>
	<div class="navbar-fixed">

		<%@ include file="pizzerianav.jsp"%>

	</div>
	<br><br><br>
	<h4>Anna palautetta!</h4>
	<br>
	<br>
	<div class="row">
		<form class="col s12" action="controller" method="POST">
			<div class="row">
				<div class="input-field col s5">
					<textarea id="textarea1" class="materialize-textarea"></textarea>
					<label for="textarea1">Kirjoita t�h�n</label>


				</div>
			</div>
<p><i>Kokemukseni oli:</i><br><br>
      <input name="group1" class="with-gap" type="radio" id="test1" />
      <label for="test1">Positiivinen</label>
    </p>
    <p>
      <input name="group1" class="with-gap" type="radio" id="test2" />
      <label for="test2">Negatiivinen</label>
    </p><br><br>

			<a class="btn" type="submit" value="submit" 
				onclick="Materialize.toast('L�hetetty!', 4000, '', function(){alert('Kiitos palautteestasi!')})">L�het�</a>


		</form>
	</div>
<footer class="page-footer green accent-4">
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
				<h5 class="white-text">Tietoa yrityksest�</h5>
				<p class="grey-text text-lighten-4">Castello � Fiori on
					perinteinen italialaishenkinen Pizzaravintola, jolla on perinteet
					syv�ll� Italian historiassa. Perustettu vuonna 1800, perustamme
					suosiomme perinteisiin ja aitoihin italialaisiin makuel�myksiin.
					Tule ja koe aitoa Venetsialaista henke� mainion viinin kera.</p>
			</div>
			<div class="col l4 offset-l2 s12">
				<h5 class="white-text">Links</h5>
				<ul>
					<li><a class="grey-text text-lighten-3" href="#!">Menu</a></li>
					<li><a class="grey-text text-lighten-3" href="#!">Group
							dining</a></li>
					<li><a class="grey-text text-lighten-3" href="#!">Order
							online</a></li>
					<li><a class="grey-text text-lighten-3" href="#!">Social
							media</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			� 2016 Late Night Show with Aarninsalo <a
				class="grey-text text-lighten-4 right" href="#!">More Links</a>
		</div>
	</div>
	</footer>
	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>
</body>
</html>