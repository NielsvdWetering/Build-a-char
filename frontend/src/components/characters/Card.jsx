import React , { useEffect, useState }from 'react'
import { DisplayField } from './DisplayField';

export const Card = ({ fields }) => {
/* takes in an array of objects with 2 key value pairs.
  The first key takes in the name of te label for the field
  The second takes in the actual content 
*/

  const [fieldList,setFieldList] = useState([{}]);
  useEffect(() => {
    setFieldList(fields);
  }, [])
  
  return (
    <div className="m-2 p-2 border-2 flex-1">
        {fieldList.map((field,index)=>(<DisplayField key={`${field.label}-${index}`} label={field.label} content={field.content}/>))}
    </div>
  )
}
