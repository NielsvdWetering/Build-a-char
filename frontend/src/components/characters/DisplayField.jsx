import React from 'react'

export const DisplayField = ({label,content}) => {
  return (
    <div className='flex gap-2'>
        <h6>{label}</h6>
        <p>{content}</p>
        </div>
  )
}
