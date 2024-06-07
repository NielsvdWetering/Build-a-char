import React , { useEffect, useState }from 'react'
import { DisplayField } from './DisplayField';
import { TextField } from './TextField';

export const Card = ({ characterObject }) => {

  const [character,setCharacter] = useState([{}]);


  useEffect(() => {
    setCharacter(characterObject);
  }, [characterObject])
  
  return (
    <div className="m-10 p-8 border-2 border-bg-primary rounded-lg flex-row text-sky-50 bg-emerald-800 w-1/2">
      <div className='font-semibold text-primary-content'>
        <DisplayField label={"name"} content={character.name}/>
        <DisplayField label={"race"} content={character.race}/>
        <DisplayField label={"class"} content={character.characterClass} />
        <TextField label={"description"} content={character.description} />
      </div>
      <div className='border-b-2 border-t-2 font-semibold text-primary-content'>
        <DisplayField label={"weapon"} content={character.weapon}/>
        <DisplayField label={"head"} content={character.armorHead}/>
        <DisplayField label={"torso"} content={character.armorTorso}/>
        <DisplayField label={"hands"} content={character.armorHands}/>
        <DisplayField label={"legs"} content={character.armorLegs}/>
        <DisplayField label={"feet"} content={character.armorFeet}/>
        <DisplayField label={"tool"} content={character.tools}/>
        </div>
        <div className='font-semibold text-primary-content'>
        <DisplayField label={"Strength"} content={character.raceAttributes.baseStrength}/>
        <DisplayField label={"Dexterity"} content={character.raceAttributes.baseDexterity}/>
        <DisplayField label={"Constitution"} content={character.raceAttributes.baseConstitution}/>
        <DisplayField label={"Intelligence"} content={character.raceAttributes.baseIntelligence}/>
        <DisplayField label={"Wisdom"} content={character.raceAttributes.baseWisdom}/>
        <DisplayField label={"Charisma"} content={character.raceAttributes.baseCharisma}/>
        </div>
    </div>
  )
}
