



fetch("http://localhost:8080/recipes")
  .then(response => response.json())
  .then(recipes => {

    const recipeList = document.getElementById("recipe-list");

    recipes.forEach(recipe => {
      const listItem = document.createElement("li");
      const link = document.createElement("a");
      link.textContent = recipe.recipeName;
      link.href = "recipe.html?id="+recipe.id;   
      listItem.appendChild(link);
      recipeList.appendChild(listItem);
});
  })
  .catch(error => console.error(error));