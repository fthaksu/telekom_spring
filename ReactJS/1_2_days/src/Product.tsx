import React from 'react'

export default function Product() {


    const obj = {
        datas: [
           { id: 10, name: "Ali", age: 30 }, 
           { id: 12, name: "Mehmet", age: 35 },
           { id: 14, name: "Ahmet", age: 40 },
           { id: 16, name: "Zehra", age: 45 },
        ]
    }


    return (
        <div>
            <h1>Welcome Product</h1>
            <table className="table">
            <thead>
                <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Age</th>
                </tr>
            </thead>
            <tbody>

            { obj.datas.map( (item, index) => {
                return (
                    <tr key={index}>
                        <th scope="row">{ item.id }</th>
                        <td>{item.name}</td>
                        <td>{ item.age }</td>
                    </tr>
                    )
            } ) }
                
                
            </tbody>
            </table>
        </div>
    )
}

