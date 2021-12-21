import axios from 'axios';
import React, { useEffect, useState } from 'react';
import NewsItem from './components/NewsItem';
import { Article, INews } from './models/INews';

function App() {

  const [newsArr, setNewsArr] = useState<Article[]>([])

  useEffect(() => {
    
    const url = "https://newsapi.org/v2/top-headlines";
    const params = {
      country: "tr",
      category: "business",
      apiKey: "38a9e086f10b445faabb4461c4aa71f8"
    }
    axios.get(url, { params: params } ).then( item => {
      if ( item.status === 200 ) {
          const obj:INews = item.data
          if ( obj.articles ) {
            setNewsArr(obj.articles)
          }
      }
    } )

  }, [])


  return (
    <>
      <div className='row'>
        { newsArr.map( (item, index) => {
            return (
              <NewsItem key={index} item={item} />
            )
        } ) }
      </div>
    </>
  );
}

export default App;
