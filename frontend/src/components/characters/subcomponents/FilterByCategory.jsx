import React from "react";

export const FilterByCategory = ({
  category,
  categoryItems,
  handleCharacterFilter,
}) => {
  return (
    <div>
      <h4 className="mt-4 w-2/3 border-b-2 pb-4 font-bold">{category}</h4>
      <ul>
        {categoryItems.map((item) => (
          <li key={item.id} className="py-2">
            <input
              className="mr-2"
              type="checkbox"
              id={item.name}
              name={item.name}
              value={item.name}
              onChange={(e) => {
                handleCharacterFilter(category, item.name, e.target.checked);
              }}
            />
            <label htmlFor={item.name}>{item.name}</label>
          </li>
        ))}
      </ul>
    </div>
  );
};
