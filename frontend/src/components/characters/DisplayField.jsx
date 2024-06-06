import React from 'react'

export const DisplayField = ({label,content}) => {
  return (
    <>
    <div className='text-xs py-1 px-2 uppercase rounded text-blueGray-600 grid grid-cols-2 gap-5'>
    <h6 className='content-start'>{label}</h6>
    <p className='content-end'>{content}</p>
    </div>
    </>
  )
}
