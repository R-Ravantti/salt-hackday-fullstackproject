'use client';

import Link from 'next/link';
import React from 'react';
import './style.css';

type MonsterDTO = {
  monster_id: number;
  monster_name: string;
  monster_group: string;
  monster_second_group: string;
}

async function addMonster(event: React.FormEvent<HTMLFormElement>) {
  event.preventDefault();
  const reqBody: MonsterDTO = {
    monster_id: event.currentTarget.monid.value,
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
  if(id) {
    const reqOptions = {
      method: 'DELETE'
    };
    const response = await fetch(`http://localhost:8080/api/monsters/${id}`, reqOptions);
  }
}

export default function Admin() {
  return (
    <div>
      <header className="header">
        <Link className="link" href={"/"}>Home</Link>
      </header>
      <div className="flexcontainer">
        <form className="adminform" id="addform" onSubmit={e => addMonster(e)}>
          <h3>Add a monster</h3>
          <input type="text" className="adminselect" name="monid" placeholder="Dex Number" />
          <input type="text" className="adminselect" name="monname" placeholder="Name" />
          <input type="text" className="adminselect" name="group1" placeholder="Egg Group 1" />
          <input type="text" className="adminselect" name="group2" placeholder="Egg Group 2" />
          <button type="submit">submit</button>
        </form>
        <form className="adminform" id="deleteform" onSubmit={e => deleteMonster(e)}>
          <h3>Delete a monster</h3>
          <input type="text" className="adminselect" name="monid" placeholder="Dex Number" />
          <button type="submit">submit</button>
        </form>
      </div>
    </div>
  )
}