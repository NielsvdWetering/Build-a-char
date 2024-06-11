import { useParams } from "react-router-dom";
import PageColumn from "../pageColumn";

export default function CharacterDisplay() {
  const { id } = useParams();

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <PageColumn>1</PageColumn>
        <PageColumn>2</PageColumn>
        <PageColumn>3</PageColumn>
      </div>
    </>
  );
}
