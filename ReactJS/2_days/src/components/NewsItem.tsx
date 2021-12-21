import React from 'react'
import { Link, NavLink } from 'react-router-dom'
import { Article } from '../models/INews'

interface IItemNews {
    item: Article
}

function NewsItem( props: IItemNews ) {
    return (
        <div className="card col-sm-4" >
            <img src={ props.item.urlToImage } className="card-img-top" alt="..." />
            <div className="card-body">
                <h5 className="card-title">{ props.item.title }</h5>
                <p className="card-text"> { props.item.description?.substring(0, 100) } </p>
                <a href={props.item.url} target='_blank' className="btn btn-primary">Detay</a>
            </div>
        </div>
    )
}

export default NewsItem
