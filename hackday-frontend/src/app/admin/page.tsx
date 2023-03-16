'use client';

import Link from 'next/link';
import React from 'react';

type MonsterDTO = {
  monster_id: number;
  monster_name: string;
  monster_group: string;
  monster_second_group: string;
}

async function addMonster(event: React.FormEvent<HTMLFormElement>) {
  event.preventDefault();
  const reqBody: MonsterDTO = { monster_id: event.currentTarget.monid.value,
    monster_name: event.currentTarget.monname.value,
    monster_group: event.currentTarget.group1.value,
    monster_second_group: event.currentTarget.group2.value
  };
  const reqOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(reqBody)
  };
  const response = await fetch('http://localhost:8080/api/monsters', reqOptions);
}

async function deleteMonster(event: React.FormEvent<HTMLFormElement>) {
  event.preventDefault();
  const id: string = event.currentTarget.monid.value;
  const reqOptions = {
    method: 'DELETE'
  };
  const response = await fetch(`http://localhost:8080/api/monsters/${id}`, reqOptions);
}

export default function Admin() {
  return (
    <div>
      <Link href={"/"}>Home</Link>
      <h3>Add a monster</h3>
      <form className="adminform" id="addform" onSubmit={e => addMonster(e)}>
        <input type="text" name="monid" placeholder="Dex Number" />
        <input type="text" name="monname" placeholder="Name" />
        <input type="text" name="group1" placeholder="Egg Group 1" />
        <input type="text" name="group2" placeholder="Egg Group 2" />
        <button type="submit">submit</button>
      </form>
      <h3>Delete a monster</h3>
      <form className="adminform" id="deleteform" onSubmit={e => deleteMonster(e)}>
        <input type="text" name="monid" placeholder="Dex Number" />
        <button type="submit">submit</button>
      </form>
    </div>
  )
}