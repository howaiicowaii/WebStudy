window.onload=function(){
let img=document.createElement('img') // <img> 태그 생성
	img.src='../image/m3.jpg'
	img.width=200
	img.height=250
	img.title="영화명" // 말풍선 도움말
	// <img src="" width="" height="" title="">
	document.body.appendChild(img)
}