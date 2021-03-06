<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="tyyli.css" rel="stylesheet" type="text/css">
<link href="styles.css" rel="stylesheet" type="text/css">
<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Tilauslomake</title>

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

</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
      
          <div class="navbar-fixed">
		<%@ include file="pizzerianav.jsp" %>
		</div>
		
	
		<h4>Tilauslomake</h4><br><br>
		<p><b>T�yt� allaolevat tiedot ja paina "Tilaa".</b><br><br>
		<c:if test="${not empty param.number}"><p>Sy�tt� ei ollut numeraalinen</p></c:if>
		 <div class="row">
    <form class="col s5" action="TilausController" method="post">
      <div class="row">
        <div class="input-field col s6">
         <i class="material-icons prefix">perm_identity</i>
          <input id="Etunimi" length="15" type="text" class="validate" name="etunimi" value="${etunimi}" required>
          <label for="Sukunimi">Etunimi</label>
        </div>
        <div class="input-field col s6">
         <i class="material-icons prefix">perm_identity</i>
          <input id="Sukunimi" length="15" type="text" class="validate" name="sukunimi" value="${sukunimi}" required>
          <label for="Sukunimi">Sukunimi</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
        <i class="material-icons prefix">phone</i>
          <input type="text" length="15" id="numero" class="validate" name="numero" value="${numero}" required>
          <label for="numero">Puhelinnumero (+358)</label>
        </div>
      </div>
      
      <h4>Yhteystiedot</h4><br>
      <div class="row">
        <div class="input-field col s12">
         <i class="material-icons prefix">location_on</i>
          <input id="Toimitusosoite" type="text" length="20" class="validate" name="toimosoite" value="${osoite}" required>
          <label for="Toimitusosoite">Toimitusosoite</label>
        </div>
      </div>
      
      <div class="row">
        <div class="input-field col s6">
         <i class="material-icons prefix">location_on</i>
          <input id="postinro" length="5" type="text" class="validate" name="postinro" value="${postinro}" required>
          <label for="postinro">Postinumero</label>
        </div>
        <div class="input-field col s6">
         <i class="material-icons prefix">location_on</i>
          <input id="postitmp" length="15" type="text" class="validate" name="postitmp" value="${tmp}" required>
          <label for="postitmp">Postitoimipaikka</label>
        </div>
      </div>
      
      <div class="row">
        <div class="input-field col s12">
         <i class="material-icons prefix">email</i>
          <input id="email" type="email" length="120" class="validate" name="email" value="${email}" required>
          <label for="email">Email</label>
        </div>
      </div>
      
<p>
      <input name="toimtapa" class="with-gap" type="radio" id="test1" value="Kotiinkuljetus" />
      <label for="test1">Kotiinkuljetus</label>
    </p>
    <p>
      <input name="toimtapa" class="with-gap" type="radio" id="test2" value="Nouto"/>
      <label for="test2">Nouto (Kuljetuksen s�de 1km)</label>
    </p><br><br>
    <p>
      <input name="maksutapa" class="with-gap" type="radio" id="test3" value="K�teinen"/>
      <label for="test3">K�teinen</label>
    </p>
    <p>
      <input name="maksutapa" class="with-gap" type="radio" id="test5" value="Kortti"/>
      <label for="test5">Kortti (Visa electron, MasterCard, Amex, Diners Club)</label>
    </p>
    
    <p>
        <input name="maksutapa" class="with-gap" type="radio" id="test4" value="Verkkomaksu"/>
        <label for="test4">Verkkomaksu</label>
    </p><br>
    
    <input type="hidden" value="${asiakasnumero}" name="asiakasnro">
    
			<button class="btn waves-effect waves-light" type="submit"
				>
				Tilaa <i class="material-icons right"></i>
			</button>
			
    </form>
  </div>

</body>
</html>