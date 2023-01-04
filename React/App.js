import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {
  let [글제목, 글제목변경] = useState(['남자코트추천', '강남 우동맛집', '파이썬독학']);
  let [따봉, 따봉변경] = useState(0);
  let post = '강북 우동 맛집';

  function 제목바꾸기(){
    글제목변경(['여자코트추천', '강남 우동맛집', '파이썬독학']);

  }

  return (
    <div className="App">
      <div className='black-nav'>
        <div>개발 blog</div>
      </div>
      <button onClick={(제목바꾸기)}>버튼</button>
      <div className='list'> 
        <h3> {글제목[0]} <span onClick={() => {따봉변경(따봉+1)}}>★</span> {따봉} </h3>
        <p>2월 17일 발행</p>
        <hr/> 
      </div>
      <div className='list'>
        <h3> {글제목[1]} </h3>
        <p>2월 18일 발행</p>
        <hr/> 
      </div>
      <div className='list'>
        <h3> {글제목[2]} </h3>
        <p>2월 19일 발행</p>
        <hr/> 
      </div>

        {/*<h4>블로그임</h4>*/}
        {/*<h4 id={post}>블로그임</h4>*/}
        {/*<h4 style={{color : 'red', fontSize : '16px'}}>{ post }</h4>*/}
        
      <h4>강남 우동 맛집</h4>
    </div>
  );
}

export default App;
