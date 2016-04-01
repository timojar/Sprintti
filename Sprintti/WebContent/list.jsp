<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="fi.omapizzeria.admin.bean.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<link href="styles.css" rel="stylesheet" type="text/css">
<link href="tyyli.css" rel="stylesheet" type="text/css">

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
		 <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>

<title>Lista</title>


</head>
<body>




	


		
		
	
		
		
		

		<div class="navbar-fixed">
		<nav>
		<div class="nav-wrapper green accent-4">
			<a href="index.jsp" class="brand-logo center"><img
				src="Kuvat/Logo.png" alt=pizza height="76" width="160"></a>
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li><a href="menu.jsp">Menu</a></li>
				<li><a href="#">Order Online</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Group Dining</a>
				<li><a href="Login.jsp"
					class="waves-effect waves-light btn">Log in</a></li>
			</ul>
		</div>
		</nav>
	</div>
		
		
		
		


		<div id=sis�lt�>






	<h2>Pizza lista</h2>

			<c:out value="${aloitusaika }"></c:out>
			<c:if test="${not empty param.added}">Uuden pizzan lis��minen onnistui!</c:if>


			<c:forEach items="${lista }" var="pizzat">

				<p>
					<c:out value="${pizzat.id }"></c:out>
				</p>
				<p>
					<c:out value="${pizzat.nimi }"></c:out>
				</p>
				<p>
					<c:out value="${pizzat.hinta }"></c:out>
				</p>

<p>
				<c:out value="${pizzat.kuvaus }"></c:out>
				</p>
				
				<c:if test="${pizzat.piiloitus == 'nosale'}"> <p>Ei ole myynniss�</p></c:if>

				<%--Pizzanpoisto: Jokaisen pizzan kohdalla on poista-nappi, jota
				painamalla l�hetet��n pizzan id-numero parametrina controller-servlettiin
				
				--%>
<br>
				<form action="poistaPizza" method="post" id="delete">
					<input type="hidden" name="tunnus" value="${pizzat.id }"> <input
						type="submit"  value="poista">

				</form>
				<br>

	<%--Pizzan piiloitus: piilota-nappi l�hett�� Pizza id-atribuutin arvon parametrina
	 controller-luokkaan, jossa se k��nnet��n int-tietotyypiksi.
	 K��nnettyn� se menee PizzaDAO-luokkaan, jossa pizza 
	 saa "nosale"-arvon tietokannan piilota-kentt��n. MenuDao-luokka tuo niit� pizzoja menuController-luokkaan
	 joissa ei ole merkint�� piilota kent�ss�. Luokka "menuController" ohjaa pizzoja ruokalistaan.
	 
				--%>
		

			<br>
		<form action="MuokkaaPizza" method="get">
		
		<input type="hidden" name="muokkausid" value="${pizzat.id }">		
<input	type="submit"  value="muokkaa">
		
		</form>

			</c:forEach>

			<form method="post" action="lisaaPitsa" id="tiedot">



				<p>Pizzan nimi:</p>
				<input type="text" name="nimi" id="pizzannimi" required>

				<p>Pizzan hinta:</p>
				<input type="text" name="hinta" id="pizzanhinta" required>
				<br>
				<br>
				
			
			<label>Valitse t�ytteet (max 6)</label>
				<br>
			
			<c:forEach items="${taytelista}" var="tayte"> 
						
		<p>
      <input type="checkbox" id="${tayte.tayteNimi}" name="taytteet" value="${tayte.tayteNimi}"/>
      
      <label for="${tayte.tayteNimi}"><c:out value="${tayte.tayteNimi}"> </c:out></label>
    </p>
			
			</c:forEach>
			
				<br>
				<br>
				
		
				
			 <input
					type="submit" id="lahetys" value="Luo pizza">			
				
			</form>


	<br>
	
	<br>	


<c:if test="${noofPages > 1}">
		
		
	
 <ul class="pagination">
    <c:forEach begin="${startindex}" end ="${noofPages}" var="i">
    <c:choose>
    
    <c:when test="${i==currentpage}">
    <li class="active"><a href="controller?page=${i}">${i}</a></li>
    </c:when>
    <c:when test="${i!=currentpage}">
    <li class="waves-effect"><a href="controller?page=${i}">${i}</a></li>
    </c:when>
    
    </c:choose>
   
   </c:forEach>
  </ul>



</c:if>

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



</body>
</html>