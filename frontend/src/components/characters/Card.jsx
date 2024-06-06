import React , { useEffect, useState }from 'react'
import { DisplayField } from './DisplayField';

export const Card = ({ characterObject }) => {

  const [character,setCharacter] = useState([{}]);
  const [armors,setArmors] = useState([{}])
  useEffect(() => {
    setCharacter(characterObject);
    setArmors(characterObject.armors)
  }, [characterObject])
  
  return (
    <div className="m-10 p-2 border-2 flex-row">
      <div className='border-2'>
        <DisplayField label={"name"} content={character.name}/>
        <DisplayField label={"race"} content={character.race}/>
        <DisplayField label={"class"} content={character.characterClass} />
        <DisplayField label={"description"} content={character.description} />
      </div>
      <div className='border-2'>
        <DisplayField label={"weapon"} content={character.weapon}/>
        <DisplayField label={"head"} content={character.armorHead}/>
        <DisplayField label={"torso"} content={character.armorTorso}/>
        <DisplayField label={"hands"} content={character.armorHands}/>
        <DisplayField label={"legs"} content={character.armorLegs}/>
        <DisplayField label={"feet"} content={character.armorFeet}/>
        <DisplayField label={"tool"} content={character.tools}/>
        </div>
        <div className='border-2'>
          Stats!!
        </div>

    </div>
  )
}
