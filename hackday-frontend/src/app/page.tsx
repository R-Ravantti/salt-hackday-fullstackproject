'use client';

import Link from 'next/link';
import React, {useState, useEffect} from 'react';

type MonsterDTO = {
  monster_id: number;
  monster_name: string;
  monster_group: string;
  monster_second_group: string;
}

type PairRequestDTO = {
  first: number;
  second: number;
}

type PairResponseDTO = {
  pair: boolean;
}

async function checkPair(event: React.FormEvent<HTMLFormElement>, setPairInfo: React.Dispatch<React.SetStateAction<String>>) {
  event.preventDefault();
  const reqBody: PairRequestDTO = {
    first: event.currentTarget.first.value,
    second: event.currentTarget.second.value
  };
  const reqOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(reqBody)
  };
  const response = await fetch('http://localhost:8080/api/monsters/compatible', reqOptions);
  const responseData: PairResponseDTO = await response.json();
  if(responseData.pair) {
    setPairInfo('Can have eggs!');
  } else {
    setPairInfo('Cannot have eggs.');
  }
}

async function getMonsters(setMonsters: React.Dispatch<React.SetStateAction<MonsterDTO[]>>) {
  const response = await fetch('http://localhost:8080/api/monsters');
  const responseJSON: MonsterDTO[] = await response.json();
  setMonsters(responseJSON);
}

export default function Home() {
  const [monsters, setMonsters] = useState<MonsterDTO[]>([]);
  const [pairInfo, setPairInfo] = useState<String>('Hello, World!');
  useEffect(() => {
    getMonsters(setMonsters);
  })
  return (
    <main>
      <Link href={"/admin"}>Admin</Link>
      <form className="monsterform" id="monsterform" onSubmit={(e) => {checkPair(e, setPairInfo)}}>
        <select className="monsterselect" id="monsterselect1" name="first">
          {monsters.map((monster) => {
            return (<option key={monster.monster_id} value={monster.monster_id}>{monster.monster_name}</option>)
          })}
        </select>
        <select className="monsterselect" id="monsterselect2" name="second">
          {monsters.map((monster) => {
            return (<option key={monster.monster_id} value={monster.monster_id}>{monster.monster_name}</option>)
          })}
        </select>
        <button type="submit">Submit</button>
      </form>
      <p>{pairInfo}</p>
    </main>
  )
}
