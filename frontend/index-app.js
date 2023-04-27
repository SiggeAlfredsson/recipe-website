



fetch("http://localhost:8080/recipes")
  .then(response => response.json())
  .then(recipes => {

    const recipeList = document.getElementById("recipe-list");

    recipes.forEach(recipe => {
      const listItem = document.createElement("li");
      const link = document.createElement("a");
      const h2 = document.createElement("h2");
      const image = document.createElement("img");
      const description = document.createElement("p");

      description.textContent = recipe.description;

      h2.textContent = recipe.recipeName; // 100% of brain XD
      
      link.href = "recipe.html?id="+recipe.id;
      
      
      image.src = "http://localhost:8080/recipes/image/"+recipe.id;
      
      
      listItem.appendChild(h2);
      listItem.appendChild(description);
      listItem.appendChild(image);

      link.appendChild(listItem);
      recipeList.appendChild(link);
});
  })
  .catch(error => console.error(error));

