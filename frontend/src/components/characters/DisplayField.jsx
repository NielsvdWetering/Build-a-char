import React from 'react'

export const DisplayField = ({label,content}) => {
  return (
    <div className='flex gap-2 '>
        <h6 className=''>{label}</h6>
        <p className=''>{content}</p>
        </div>
  )
}
