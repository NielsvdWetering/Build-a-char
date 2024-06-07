import React , { useEffect, useState }from 'react'
import { DisplayField } from './DisplayField';

export const Card = ({ characterObject }) => {

  const [character,setCharacter] = useState([{}]);


  useEffect(() => {
    setCharacter(characterObject);
  }, [characterObject])
  
  return (
    <div className="m-10 p-8 border-2 border-emerald-950 rounded-lg flex-row text-sky-50 bg-emerald-800">
      <div className='border-b-2 pb-2 border-emerald-950'>
        <DisplayField label={"name"} content={character.name}/>
        <DisplayField label={"race"} content={character.race}/>
        <DisplayField label={"class"} content={character.characterClass} />
        <DisplayField label={"description"} content={character.description} />
      </div>
      <div className='border-b-2 border-emerald-950 mt-5 pb-2'>
        <DisplayField label={"weapon"} content={character.weapon}/>
        <DisplayField label={"head"} content={character.armorHead}/>
        <DisplayField label={"torso"} content={character.armorTorso}/>
        <DisplayField label={"hands"} content={character.armorHands}/>
        <DisplayField label={"legs"} content={character.armorLegs}/>
        <DisplayField label={"feet"} content={character.armorFeet}/>
        <DisplayField label={"tool"} content={character.tools}/>
        </div>
        {character.raceAttributes && 
        <div className='mt-5'>
        <DisplayField label={"Strength"} content={character.raceAttributes.baseStrength}/>
        <DisplayField label={"Dexterity"} content={character.raceAttributes.baseDexterity}/>
        <DisplayField label={"Constitution"} content={character.raceAttributes.baseConstitution}/>
        <DisplayField label={"Intelligence"} content={character.raceAttributes.baseIntelligence}/>
        <DisplayField label={"Wisdom"} content={character.raceAttributes.baseWisdom}/>
        <DisplayField label={"Charisma"} content={character.raceAttributes.baseCharisma}/>
        </div>}
    </div>
  )
}
