<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.domain.MembersDTO" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        
        <title>옥독캣</title>
        <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-neo.css" rel="stylesheet">
        <link href="${root}/resources/bootstrap/css/mypageStyles.css" rel="stylesheet" />
        <link href="${root}/resources/bootstrap/js/bootstrap.min.js"/>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${root}/resources/bootstrap/js/datatables-simple-demo.js"></script>
     	<script>
	     function login(){
	    	  location.href = "${pageContext.servletContext.contextPath}/user/login";
	    	  }
      
	      function mypage(){
	      		location.href = "${pageContext.servletContext.contextPath}/mypage/mypage";
	      }
	      
	      function logout() {
	  		if (confirm("로그아웃 하시겠습니까?")) {
	  		location.href = "${pageContext.servletContext.contextPath}/user/logout";
	  	 	}
	  	}
	      
	      function main(){
	    		location.href = "${pageContext.servletContext.contextPath}/main/main";
	   	 }
	      function logplz() {
	    	  var uid ='<%=(String)session.getAttribute("SESS_EMAIL")%>';
	   
	   			console.log(uid+"뿡");
	    	  if(uid==null){
	    	  alert("로그인해라 조은말로할때...");
	    	  }
	      }
	      
     	</script>
     	<!-- 폰트 -->
     	<!-- font-family: 'NanumSquareNeoLight';
		font-family: 'NanumSquareNeo';
		font-family: 'NanumSquareNeoBold';
		font-family: 'NanumSquareNeoExtraBold';
		font-family: 'NanumSquareNeoHeavy'; -->
		
	     <style type="text/css">
		.nanum{ font-family: 'NanumSquareNeo'; }
		.nanumB{font-family: 'NanumSquareNeoBold';}		
		</style>
	<style> 
		
       a:hover{
                background-color: #feeaa5;
            }
            .main{
            padding-top: 0.7cm;
            padding-left: 1.0cm;
            padding-right : 1.5cm;
            padding-bottom : 3cm;
            height: 120px;
            }         
            .bg-yellow {
              --bs-bg-opacity: 1;
              background-color: #feeaa5 !important;
         }
         .main1{
         border-bottom : 1px solid #645326;
          padding-bottom : 2px;
          padding-top : 2px;
         }
         .tab{
             padding-bottom : 0;
             padding-top : 0;
            border-bottom : 1px solid #645326;
            border-top : 1px solid #645326;
         }
         
         .img_main{
         width: 60%;
          margin: 0px auto;
          display: block;
          width: 250px; height: 90px;
          }
          .mainpage{
         background-color: #f9f8f3;
          }
          
        </style>
        
    </head>
   <body class="sb-nav-fixed nanum"> 
           <nav class="main1 sb-topnav2 navbar navbar-expand; navbar-dark bg-yellow" >
          <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-0 my-md-0 mt-sm-0 ">
                 <div class="input-group">
                <% String id = (String)session.getAttribute("SESS_EMAIL"); %>
              
        <%  if( id != null) { %>
        <div style="margin-top:5px;">♡${sessionScope.SESS_NICKNAME}님 환영합니다♡</div>
                   <button type="button" class="btn" onclick="logout();" style="font-size: 14px;">로그아웃</button>
                   <button type="button" class="btn" onclick="mypage();" style="font-size: 14px;">마이페이지</button>                  
                   
            <%} else{%>
                <button type="button" class="btn" onclick="login();" style="font-size: 14px;">로그인</button>                 
             
            <%}  %>
                </div>
            </form>      
            </nav>
                    
         <!-- 로고 -->              
       <nav class="main bg-white" >
         <a class="mainlogo" href="${root}/main/main" >
         <img class = "img_main" src="../resources/image/logo.png" style="width: 250px; height: 90px;"/>
         </a>
        </nav> 
         <nav class="tab sb-topnav2 navbar navbar-expand; bg-white" >
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/pet/petall"><b>공고</b></a> 
             <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/shel/shelall"><b>보호소</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/with/withca"><b>위드펫</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/community/clist" onclick="logplz();"><b>커뮤니티</b></a>
			 <a class="pt-3 pb-3 flex-sm-fill text-sm-center nav-link" href="${pageContext.servletContext.contextPath}/notice/nlist"><b>공지사항</b></a>
            </nav> 

                
    <main class = "mainpage nanum">

  <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel" data-bs-theme="light">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2" class="active" aria-current="true"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
    </div>
    <div class="carousel-inner mybanner" >
      <div class="carousel-item " >
      <img class = "mainbanner" src="../resources/image/mainb1.png" style="max-width: 100%; height:500px; overflow:hidden; object-fit: none;"/>
     
        <div class="container">        
          <div class="carousel-caption text-start">                                  
          </div>
        </div>
      </div>
      <div class="carousel-item active ">
       <img class = "mainbanner" src="../resources/image/mainb3.png" style="max-width: 100%; height:500px; overflow:hidden; object-fit: none;"/>
      </div>
      <div class="carousel-item">
      <img class = "mainbanner" src="../resources/image/mainb2.png" style="max-width: 100%; height:500px; overflow:hidden; object-fit: none;"/>
        <div class="container">
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
      </button>
  </div>


  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <!--  <div class="row">
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>Some representative placeholder content for the three columns of text below the carousel. This is the first column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
      <div class="col-lg-4">
      	
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>Another exciting bit of representative placeholder content. This time, we've moved on to the second column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>인기글</title><rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect></svg>
        <h2 class="fw-normal">인기글</h2>
        <p>And lastly this, the third column of representative placeholder content.</p>
        <p>And lastly this, the third column.</p>
        <p><a class="btn btn-secondary" href="#">View details »</a></p>
      </div>
    </div> -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-6"  style="margin-left:30px">
      <br><br><br><br><br><br>
        <h3 class="featurette-heading fw-normal lh-1 nanumB">옥독캣에 오신 걸 환영합니다!<span class="text-body-secondary"></span></h3>
       <br>  
        <p class="lead" style="font-size:17px">'옥독캣'은 유기 동물들에게 새로운 가족과 행복한 삶을 선사하기 위해 만들어진 공간입니다.
        우리와 함께 이 작은 세상에서 온전한 포옹과 따스함을 전달할 수 있는 동반자를 찾아보세요.
        </p>
      </div>
      <div class="col-md-5 ms-5">
      <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5001.png" style="width: 100%; height:100%;"/>      
    </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-6 order-md-2" style="margin-left:50px">
      	<br><br><br><br><br>     
        <h3 class="featurette-heading fw-normal lh-1 nanumB"> 유기 동물 공고 및 서비스 정보 <span class="text-body-secondary"></span></h3>
        <br>
        <p class="lead" style="font-size:17px">우리는 전국 각지에서 구조된 동물들을 소개하고,
        				보호소 정보 및 반려 동반 가능 서비스 정보를 공유하여 활발한 반려동물 입양 사회를 만들어갑니다. <br><br>
        				또한, 유기 동물 입양 후 건강하고 행복한 생활을 위해 반려 동반 가능 서비스 정보 및 케어(미용, 병원) 서비스 정보를 공유하고 있습니다.</p>
      </div>
      <div class="col-md-5 order-md-0">
      <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5002.png" style="width: 100%; height:100%;"/>            
      </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-6" style="margin-left:30px">
      <br><br><br><br><br><br><br>
        <h3 class="featurette-heading fw-normal lh-1 nanumB">서로의 정보를 공유하는 커뮤니티<span class="text-body-secondary"></span></h3>
       	<br>
        <p class="lead" style="font-size:17px"> 커뮤니티 게시판을 통하여 각자 가진 반려동물에 관한 정보를 공유할 수 있으며,
        '찾습니다'&'발견했습니다'와 같은 게시물로 잃어버린 반려동물을 추적해 볼 수 있습니다.</p>   
     
      </div>
      <div class="col-md-5  ms-5">
            <img class = "bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" src="../resources/image/main5003.png" style="width: 100%; height:100%;"/>               
      </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
   <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">OkDogCat &copy; Happy Project</div>
                            <p class="float-end"><a href="#">Back to top</a></p>

                        </div>
                    </div>
                </footer>
</main>
               
    </body>
</html>