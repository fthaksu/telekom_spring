import { useState } from "react";

function App() {

  // array
  const users = [ "Erkan", "Mehmet", "Mustafa", "Zehra", "Ayşe" ];

  const [data, setData] = useState("")
  const [surname, setSurname] = useState("")
  const [arr, setArr] = useState(users)

  const fncSend = (num:number) => {
    if ( data === "" ) {
        alert("Lütfen Adınızı Giriniz!")
    }else {
      console.log(` fncSend Call `, data)
      arr.push(data); // array add item
      setArr(arr);
      setData("")
    }

  }


  const deleteItem = ( index:number ) => {
    console.log(`index`, index)

    const newArr = Object.assign([], arr);
    newArr.splice(index, 1);
    setArr(newArr)

  }


  return (
    <>

      <input value={data} onChange={ (evt) => setData( evt.target.value )  } placeholder="Adınız!" />
      <input onChange={ (evt) => setSurname( evt.target.value )  } placeholder="Soyadı!" />
      <button onClick={ (evt) => fncSend(100) } >Gönder</button>
      <h5> {data} </h5>
      <h5> {surname} </h5>

      <ul>
        { arr.map( (item, index) => { 
          return ( <li key={index}> { item } <button onClick={ (evt) => deleteItem(index) } >Sil</button> </li> )
         } ) }
      </ul>

    </>
  );


}

export default App;
