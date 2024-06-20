import React from "react";

export const FilterByCategory = ({
  category,
  categoryItems,
  handleCharacterFilter,
}) => {
  return (
    <div>
      <h4 className="mt-4 w-2/3 border-b-4 border-double border-primary pb-4 font-bold">
        {category}
      </h4>
      <ul>
        {categoryItems.map((item) => (
          <li key={item.id} className="py-2">
            <input
              className="before:content[''] before:bg-blue-gray-500 checked:primary peer relative mr-2 h-5 w-5 cursor-pointer appearance-none rounded-md border border-primary transition-all before:absolute before:left-2/4 before:top-2/4 before:block before:h-12 before:w-12 before:-translate-x-2/4 before:-translate-y-2/4 before:rounded-full before:opacity-0 before:transition-opacity after:absolute after:left-0 after:top-0 after:flex after:h-full after:w-full after:items-center after:justify-center checked:after:text-white checked:after:content-['🐻'] hover:before:opacity-10"
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
