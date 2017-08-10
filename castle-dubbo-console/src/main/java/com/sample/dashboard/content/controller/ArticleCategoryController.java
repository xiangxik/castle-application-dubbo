package com.sample.dashboard.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.castle.repo.domain.Node;
import com.castle.repo.domain.Result;
import com.castle.repo.domain.Tree;
import com.castle.repo.domain.TreeHelper;
import com.google.common.collect.Sets;
import com.querydsl.core.types.Predicate;
import com.sample.business.content.entity.ArticleCategoryEntity;
import com.sample.business.content.entity.QArticleCategoryEntity;
import com.sample.business.content.service.ArticleCategoryService;
import com.sample.dashboard.support.EntityController;

@Controller
@RequestMapping("/content/articleCategory")
public class ArticleCategoryController extends EntityController<ArticleCategoryEntity, Long> {

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@Override
	public Page<ArticleCategoryEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

	@RequestMapping(value = "/addChild", method = RequestMethod.POST)
	@ResponseBody
	public Node<ArticleCategoryEntity> doAddChild(
			@RequestParam(value = "parent", required = false) ArticleCategoryEntity parent) {
		ArticleCategoryEntity articleCategory = articleCategoryService.initDomain();
		articleCategory.setParent(parent);
		articleCategory.setName("新分类");

		Long childCount = articleCategoryService
				.count(parent == null ? QArticleCategoryEntity.articleCategoryEntity.parent.isNull()
						: QArticleCategoryEntity.articleCategoryEntity.parent.eq(parent));
		articleCategory.setSortNo(childCount.intValue());
		Node<ArticleCategoryEntity> node = TreeHelper.toNode(articleCategoryService.save(articleCategory), null);
		node.setText(node.getData().getName());
		return node;
	}

	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public Tree<ArticleCategoryEntity> doTree(
			@RequestParam(value = "checked", required = false) ArticleCategoryEntity[] checked, Predicate predicate) {
		Node<ArticleCategoryEntity> rootNode = new Node<>();
		ArticleCategoryEntity rootCategory = new ArticleCategoryEntity();
		rootCategory.setName("全部");
		rootNode.setData(rootCategory);

		Tree<ArticleCategoryEntity> tree = articleCategoryService.findTree(predicate, rootNode).setTextProperty("name");
		tree.makeExpandAll();

		if (checked != null && checked.length > 0) {
			tree.setChecked(Sets.newHashSet(checked));
			tree.makeCheckable();
		}
		return tree;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	@ResponseBody
	public Result doSort(@RequestParam(value = "source") ArticleCategoryEntity source,
			@RequestParam(value = "target") ArticleCategoryEntity target, String action) {
		return Result.success().addProperties("data", articleCategoryService.sort(source, target, action));
	}

}
